function validateSignupForm() {
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const errorMessage = document.getElementById('error-message');

    // 이름 입력 확인
    if (name === '') {
        errorMessage.textContent = '이름을 입력하세요.';
        errorMessage.classList.remove('hidden');
        return false;
    }

    // 이메일 형식 검증
    if (!validateEmail(email)) {
        errorMessage.textContent = '올바른 이메일 형식을 입력하세요.';
        errorMessage.classList.remove('hidden');
        return false;
    }

    // 비밀번호 길이 검증
    if (password.length < 6) {
        errorMessage.textContent = '비밀번호는 최소 6자리여야 합니다.';
        errorMessage.classList.remove('hidden');
        return false;
    }

    // 비밀번호와 비밀번호 확인 일치 여부 검증
    if (password !== confirmPassword) {
        errorMessage.textContent = '비밀번호가 일치하지 않습니다.';
        errorMessage.classList.remove('hidden');
        return false;
    }

    errorMessage.classList.add('hidden'); // 오류가 없을 때는 오류 메시지를 숨김
    alert('회원가입 성공!');
    return true;
}

// 이메일 형식 검증 함수
function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}
