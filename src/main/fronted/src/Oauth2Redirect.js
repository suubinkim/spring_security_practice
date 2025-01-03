import { useEffect } from 'react';
import {useNavigate, useSearchParams} from 'react-router-dom';

const OAuth2Redirect = () => {
    const [queryParams] = useSearchParams();

    useNavigate();
    useEffect(() => {
        const OAuth2JwtHeaderFetch = async () => {
            const name = queryParams.get('name'); // 쿼리 파라미터에서 'name' 가져오기
            try {
                const response = await fetch("http://localhost:8011/oauth2-jwt-header", {
                    method: "POST",
                    credentials: "include",
                });

                if (response.ok) {
                    // 응답 헤더에서 access token 저장
                    const accessToken = response.headers.get("access");
                    if (accessToken) {
                        window.localStorage.setItem("access", accessToken);
                    }

                    // 쿼리 파라미터의 name 값 저장
                    if (name) {
                        window.localStorage.setItem("name", name);
                    }
                } else {
                    alert('접근할 수 없는 페이지입니다.');
                }
                // eslint-disable-next-line no-restricted-globals
                location.href = "/"
            } catch (error) {
                console.error("Error during OAuth2 redirect handling:", error);
            }
        };

        OAuth2JwtHeaderFetch();
    }, [queryParams]);
};

export default OAuth2Redirect;
