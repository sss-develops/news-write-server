package project.goorm.newswriteserver.common.config;

import project.goorm.newswriteserver.business.web.application.member.MemberFeignClient;

public class MemberFeignClientImpl implements MemberFeignClient {
    @Override
    public Boolean memberExistsByMemberId(Long memberId) {

        if (memberId == null) {
            return Boolean.FALSE;
        }

        if (memberId > 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
