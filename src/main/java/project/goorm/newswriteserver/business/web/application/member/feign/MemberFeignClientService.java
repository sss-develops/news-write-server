package project.goorm.newswriteserver.business.web.application.member.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class MemberFeignClientService {

    private final MemberFeignClient memberFeignClient;

    public MemberFeignClientService(MemberFeignClient memberFeignClient) {
        this.memberFeignClient = memberFeignClient;
    }

    @CircuitBreaker(name = "existMember", fallbackMethod = "throwMemberFeignEx")
    public Boolean existByMemberId(Long memberId) {
        return memberFeignClient.memberExistsByMemberId(memberId);
    }

    private Boolean throwMemberFeignEx(Throwable t) {
        throw new IllegalArgumentException("페인 클라이언트 에러!");
    }
}
