import {useState} from "react";

const Home = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const fetchLogin = async () => {

        await fetch('http://localhost:8011/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: "include",
            body: JSON.stringify({username, password}),
        })
            .then(response => {
                // 응답이 성공적인지 확인
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                console.log('응답 성공:', response);
                return response;
            })
            .then(response => {
                // 헤더에서 특정 값 추출
                for (let [key, value] of response.headers) {
                    console.log(`${key} = ${value}`);
                }
                const token = response.headers.get('access'); // 예: 토큰 값
                if (!token) {
                    throw new Error('Authorization-Token 헤더가 없습니다.');
                }
                return token;
            })
            .then(token => {
                // 추출한 헤더 값을 사용하여 두 번째 fetch 요청
                window.localStorage.setItem("access", token);
                return getUserInfo(token)
            })
            .then(data => {
                console.log('두 번째 요청 응답 데이터:', data);
                // const messageElement = document.getElementById('responseMessage');
                // messageElement.textContent = `${data.data}`;
            })
            .catch(error => {
                console.error('에러 발생:', error);
                // const messageElement = document.getElementById('responseMessage');
                // messageElement.textContent = `anonymousUser`;
            });
    }

    async function getUserInfo(token) {
        try {
            const response = await fetch('http://localhost:8011/user', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}` // 헤더 값으로 토큰 추가
                }
            });

            if (!response.ok) {
                // HTTP 응답 상태 코드 확인
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            // JSON 응답 파싱
            return await response.json(); // 데이터 반환
        } catch (error) {
            console.error('Error fetching user info:', error); // 오류 처리
            throw error; // 필요한 경우 호출한 곳에서 추가 처리하도록 예외 다시 던지기
        }
    }

    const loginHandler = async (e) => {
        e.preventDefault();
        const credentials = { username, password };
        fetchLogin(credentials);
    }

    return (
        <div>
            <h1>Home</h1>
            <form id="loginForm" onSubmit={loginHandler}>
                <p><span className='label'>Username</span><input className='input-class' type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder='username' /></p>
                <p><span className='label'>Password</span><input className='input-class' type="password" autoComplete='off' value={password} onChange={(e) => setPassword(e.target.value)} placeholder='password' /></p>
                <button type="submit">Login</button>
            </form>
            <div className='social-login' style={{textAlign: "-webkit-center"}}>
                <table className="table table-striped">
                    <tbody>
                    <tr>
                        <td><a href="http://localhost:8011/oauth2/authorization/naver">naver</a></td>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <td><a href="http://localhost:8011/oauth2/authorization/google">google</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    );
};

export default Home;
