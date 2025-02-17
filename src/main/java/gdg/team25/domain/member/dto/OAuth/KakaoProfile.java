package gdg.team25.domain.member.dto.OAuth;

import lombok.Getter;

@Getter
public class KakaoProfile {
    private Long id;
    private String connected_at;
    private KakaoAccount kakao_account;

    @Getter
    public static class KakaoAccount {
        private Boolean profile_needs_agreement;
        private Profile profile;
        private Boolean has_email;
        private String gender;
        private String birthYear;
        private Boolean email_needs_agreement;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
        private String email;
        private Boolean has_age_range;
        private Boolean age_range_needs_agreement;
        private Boolean has_birthday;
        private Boolean birthday_needs_agreement;
        private Boolean has_gender;
        private Boolean gender_needs_agreement;
        private String phone_number;

        @Getter
        public static class Profile {
            private String nickname;
            private String thumbnail_image_url;
            private String profile_image_url;
        }
    }
}