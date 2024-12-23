import fetchReissue from "./fetchReissue";

// 권한이 있는 페이지 접근 시 access 토큰을 검증
const fetchAuthorizedPage = async (url) => {
    try {
        const response = await fetch(url, {
            method: "GET",
            credentials: "include",
            headers: {
                "Authorization": 'Bearer ' + window.localStorage.getItem("access"), // local storage 의 access 토큰을 요청 헤더에 추가
            },
        });

        if (response.ok) {
            return await response.text();
        } else if (response.status === 401){
            // unauthorized code -> 1. 재발급 요청  2. 재발급 요청 성공 or 실패 핸들링
            // 재발급 성공 시 다시 권한 페이지 fetch, 재발급 실패 시 로그인 페이지로
            const reissueSuccess = await fetchReissue();
            if (reissueSuccess) {
                await fetchAuthorizedPage(url);
            } else {
                // useLocation 으로 얻은 path 를 useNavigate 을 사용해 state 에 set
                alert('토큰 재발급 실패');
                // eslint-disable-next-line no-restricted-globals
                location.href = "/"
            }
        } else {
            alert('관리자가 아닙니다.');
            // eslint-disable-next-line no-restricted-globals
            location.href = "/"
        }
    } catch (error) {
        console.log('error: ', error);
    }
}

export default fetchAuthorizedPage;
