const btnShareTw = document.querySelector('#shareTw');

btnShareTw.addEventListener('click', () => {
    const sendText = '내가 살 집을 찾고 있다면? 지금 바로 Happy House로!';
const pageUrl = 'localhost:8080/home';
window.open(`https://twitter.com/intent/tweet?text=${sendText}&url=${pageUrl}`);
})

