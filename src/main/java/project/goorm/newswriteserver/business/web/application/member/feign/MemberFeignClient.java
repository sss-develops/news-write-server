package project.goorm.newswriteserver.business.web.application.member;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.goorm.newswriteserver.common.configuration.FeignRetryConfiguration;

@FeignClient(
        name = "${api.authentication-server.name}",
        url = "${api.authentication-server.url}",
        configuration = {FeignRetryConfiguration.class}
)
public interface MemberFeignClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/member/exist/{memberId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Boolean memberExistsByMemberId(@PathVariable("memberId") Long memberId);
}
