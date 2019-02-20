package ir.asta.tutorial.dl.util;

import ir.asta.tutorial.dl.service.impl.ContentServiceImpl;
import ir.asta.wise.core.security.SecurityContextUtils;
import ir.asta.wise.core.security.model.Post;
import org.springframework.stereotype.Component;

public class DlUtils {

    public static Long getCurrentUserConfidentialLevelIndex() {
        Long confidentialLevelIndex = 0L;
        Post currentPost = SecurityContextUtils.getCurrentUser().getCurrentPost();
        if (currentPost != null) {
            confidentialLevelIndex = currentPost.getConfidentialLevel();
        }
        return confidentialLevelIndex;
    }

    public static boolean isCurrentUserAuthorizedToChangeContentStatus(){
        return SecurityContextUtils.isCurrentUserAuthorizedForPermission(ContentServiceImpl.CONTENT_STATUS_CHANGE_PERMISSION_CODE);
    }
}
