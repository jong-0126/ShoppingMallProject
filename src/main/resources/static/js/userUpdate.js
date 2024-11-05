function validateUpdateForm() {
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const confirmPassword = document.getElementById("confirm-password").value.trim();
    const tel = document.getElementById("tel").value.trim();
    const errorMessage = document.getElementById("error-message");

    // 초기화
    errorMessage.classList.add("hidden");

    // 이름 유효성 검사
    if (name === "") {
        errorMessage.textContent = "이름을 입력하세요.";
        errorMessage.classList.remove("hidden");
        return false;
    }

    // 이메일 유효성 검사
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        errorMessage.textContent = "유효한 이메일을 입력하세요.";
        errorMessage.classList.remove("hidden");
        return false;
    }

    // 비밀번호 확인
    if (password !== "" && password !== confirmPassword) {
        errorMessage.textContent = "비밀번호가 일치하지 않습니다.";
        errorMessage.classList.remove("hidden");
        return false;
    }

    // 전화번호 유효성 검사
    if (tel === "") {
        errorMessage.textContent = "전화번호를 입력하세요.";
        errorMessage.classList.remove("hidden");
        return false;
    }

    // 모든 유효성 검사를 통과하면 true 반환
    return true;
}
