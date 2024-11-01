
function validateForm() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const errorMessage = document.getElementById('error-message');

    // 입력이 없는 경우 오류 메시지 표시
    if (email === '' || password === '') {
        errorMessage.textContent = '이메일과 비밀번호를 모두 입력하세요.';
        errorMessage.classList.remove('hidden');
        return false;
    }

    // 이메일 형식 및 비밀번호 길이 검증
    if (!validateEmail(email)) {
        errorMessage.textContent = '올바른 이메일 형식을 입력하세요.';
        errorMessage.classList.remove('hidden');
        return false;
    }

    if (password.length < 6) {
        errorMessage.textContent = '비밀번호는 최소 6자리여야 합니다.';
        errorMessage.classList.remove('hidden');
        return false;
    }

    errorMessage.classList.add('hidden'); // 오류가 없을 때는 오류 메시지를 숨김
    return true;
}

// 이메일 형식 검증 함수
function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}
