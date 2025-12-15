package com.hades.hadescoffee.application.utils;

public class EmailTemplate {

    public static String signupOtp(String otp) {
        return """
            Xin chào,

            Cảm ơn bạn đã đăng ký tài khoản tại hệ thống quản lý cửa hàng cà phê.

            Mã OTP xác thực tài khoản của bạn là:
            -----------------------------------
            OTP: %s
            -----------------------------------

            Mã OTP có hiệu lực trong vòng 1 phút.
            Vui lòng không chia sẻ mã này cho bất kỳ ai.

            Trân trọng,
            Hệ thống quản lý cửa hàng cà phê
            """.formatted(otp);
    }

    public static String resetPasswordOtp(String otp) {
        return """
            Xin chào,

            Chúng tôi đã nhận được yêu cầu khôi phục mật khẩu cho tài khoản của bạn.

            Mã OTP xác thực khôi phục mật khẩu của bạn là:
            -----------------------------------
            OTP: %s
            -----------------------------------

            Mã OTP có hiệu lực trong vòng 1 phút.
            Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email.

            Trân trọng,
            Hệ thống quản lý cửa hàng cà phê
            """.formatted(otp);
    }

    public static String resetPasswordSuccess(String newPassword) {
        return """
            Xin chào,

            Mật khẩu mới cho tài khoản của bạn đã được tạo thành công.

            Thông tin đăng nhập mới:
            -----------------------------------
            Mật khẩu mới: %s
            -----------------------------------

            Vui lòng đăng nhập và đổi mật khẩu ngay để đảm bảo an toàn.

            Trân trọng,
            Hệ thống quản lý cửa hàng cà phê
            """.formatted(newPassword);
    }
}

