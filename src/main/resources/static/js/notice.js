function showNoticeDetail(title, content) {
    document.getElementById('popup-title').innerText = title;
    document.getElementById('popup-content').innerText = content;
    document.getElementById('notice-popup').classList.remove('hidden');
}

function closePopup() {
    document.getElementById('notice-popup').classList.add('hidden');
}
