package com.logos.s3.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {
    private final FileExtensionUtils fileExtensionUtils;
    private final AmazonS3 amazonS3;

    public String uploadImage(MultipartFile media) throws UnsupportedEncodingException {
        //원본 미디어 파일 이름 가져오기
        String originalFilename = media.getOriginalFilename();
        log.info("curFile = {}",media.getOriginalFilename());

        if(originalFilename == null || originalFilename.isBlank()){
            log.error("파일 이름이 없습니다.");
            return null;
        }

        //파일 이름 생성
        String fileName = buildFileName(originalFilename);
        String folderName = fileName.substring(0, fileName.indexOf(fileExtensionUtils.getCATEGORY_SEPARATOR()));
        String bucketName = fileExtensionUtils.getBUCKET()+ "/"+ folderName;

        //S3업로드
        if(!uploadToS3(fileName,bucketName,media)){
            log.error("파일 쓰기 중 에러");
            return null;
        }

        URL url = amazonS3.getUrl(bucketName, fileName);

        String decodedURL = URLDecoder.decode(url.toString(), "UTF-8");
        return decodedURL;
    }

    private boolean uploadToS3(String fileName,String bucketName,MultipartFile media){
        try (InputStream inputStream = media.getInputStream()) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(media.getContentType());
            objectMetadata.setContentLength(media.getBytes().length);

            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }
        catch (AmazonServiceException e){
            log.error("uploadToAWS AmazonServiceException filePath={}, error={}",media.getResource(), e.getMessage());
            return false;
        }
        catch (SdkClientException e){
            log.error("uploadToAWS SdkClientException filePath={}, error={}",media.getResource(), e.getMessage());
            return false;
        }
        catch (IOException e) {
            log.error("IO Exception Error = {}",e.getMessage());
            return false;
        }
        return true;
    }

    private String buildFileName(String originalFileName) {
        String category = "etc";
        int fileExtensionIndex = originalFileName.lastIndexOf(fileExtensionUtils.getFILE_EXTENSION_SEPARATOR());
        String fileExtension = originalFileName.substring(fileExtensionIndex);

        if(fileExtensionUtils.getImage_extension().contains(fileExtension)){
            category = "img";
        }
        else if(fileExtensionUtils.getVideo_extension().contains(fileExtension)){
            category = "video";
        }

        return category + fileExtensionUtils.getCATEGORY_SEPARATOR() + LocalDateTime.now() + fileExtensionUtils.getUUID_SEPARATOR() + UUID.randomUUID() + fileExtension;
    }

    public String deleteImage(List<String> deleteParams) {
        for(String filePath : deleteParams){
            try {
                amazonS3.deleteObject(new DeleteObjectRequest(fileExtensionUtils.getBUCKET(), filePath));
            } catch (AmazonServiceException e){
                log.error("delete from AWS AmazonServiceException filePath={}, yyyymm={}, error={}", e.getMessage());
            }
            catch (SdkClientException e){
                log.error("delete from AWS SdkClientException filePath={}, error={}", e.getMessage());
            }catch (Exception e){
                log.error("delete from AWS error={}",e.getMessage());
            }
        }

        return "Delete Image Successfully";
    }
}
