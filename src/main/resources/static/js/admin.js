// admin_script.js

document.querySelectorAll('.nav-link').forEach(link => {
    link.addEventListener('click', function (e) {
        e.preventDefault();

        // 모든 링크에서 active 클래스 제거
        document.querySelectorAll('.nav-link').forEach(nav => nav.classList.remove('active'));
        this.classList.add('active');

        // 모든 섹션 숨김
        document.querySelectorAll('.admin-section').forEach(section => section.style.display = 'none');

        // 선택된 섹션 표시
        const sectionId = this.getAttribute('data-section');
        document.getElementById(sectionId).style.display = 'block';
    });
});
