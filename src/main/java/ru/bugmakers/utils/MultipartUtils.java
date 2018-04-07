package ru.bugmakers.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.*;

/**
 * Created by Ivan
 */
public class MultipartUtils {

    /**
     * Поиск json части
     *
     * @param request запрос
     * @return json
     * @see MultipartRequest
     * @see MultipartFile
     */
    public static MultipartFile findJsonPart(MultipartRequest request) {
        if (request == null) return null;
        MultipartFile jsonPart = null;
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile part = request.getFile(fileNames.next());
            if (part != null
                    && part.getContentType() != null
                    && checkContentType(part, MediaType.APPLICATION_JSON_VALUE)) {
                jsonPart = part;
                break;
            }
        }
        return jsonPart;
    }

    /**
     * Поиск аватара
     *
     * @see MultipartUtils#findByPartName(MultipartRequest, String)
     */
    public static MultipartFile findAvatarPart(MultipartRequest request) {
        MultipartFile avatar = findByPartName(request, "avatar");
        if (checkContentType(avatar, MediaType.IMAGE_JPEG_VALUE)) {
            return avatar;
        } else {
            return null;
        }
    }

    /**
     * Поиск всех частей типа {@link MediaType#IMAGE_JPEG}
     *
     * @param request {@link MultipartRequest}
     * @return коллекция частей типа {@link MediaType#IMAGE_JPEG}
     */
    public static Collection<MultipartFile> findImageParts(MultipartRequest request) {
        if (request == null) return null;
        List<MultipartFile> imageParts = new ArrayList<>();
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (checkContentType(file, MediaType.IMAGE_JPEG_VALUE)) {
                imageParts.add(file);
            }
        }
        return CollectionUtils.isNotEmpty(imageParts) ? imageParts : null;
    }

    /**
     * Поиск части по названию
     *
     * @param request  запрос
     * @param partName название части
     * @return часть с назнванием partName
     * @see MultipartFile
     */
    private static MultipartFile findByPartName(MultipartRequest request, String partName) {
        if (request == null || StringUtils.isBlank(partName)) return null;
        MultipartFile part = null;
        Map<String, MultipartFile> parts = request.getFileMap();
        if (!parts.isEmpty()) {
            part = parts.get(partName);
        }
        return part;
    }

    /**
     * Проверка ContentType у {@link MultipartFile}
     *
     * @param multipartFile {@link MultipartFile}
     * @param contentType   проверяемый ContentType
     * @return true/false
     */
    private static boolean checkContentType(MultipartFile multipartFile, String contentType) {
        if (multipartFile == null || multipartFile.getContentType() == null || contentType == null) return false;
        return multipartFile.getContentType().toLowerCase().startsWith(contentType);
    }

}
