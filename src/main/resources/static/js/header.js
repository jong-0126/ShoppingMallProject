function logout() {
    if (confirm('정말 로그아웃 하시겠습니까?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '/logout'; // 로그아웃 URL 설정
        document.body.appendChild(form);
        form.submit(); // 폼 제출
    }
}