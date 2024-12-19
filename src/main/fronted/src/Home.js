import {useLogin} from "./AuthContext";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

const Home = () => {
    useNavigate();
    const {isLoggedIn, loginUser} = useLogin();
    const {setIsLoggedIn, setLoginUser} = useLogin();
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
                const name = response.headers.get('name');
                if (!token) {
                    throw new Error('Authorization-Token 헤더가 없습니다.');
                }
                window.localStorage.setItem("access", token);
                window.localStorage.setItem("name", name);

                // eslint-disable-next-line no-restricted-globals
                location.href = "/"
            })
            .catch(error => {
                console.error('에러 발생:', error);
            });
    }

    const fetchLogout = async () => {
        try {
            // access token 삭제 (로컬 스토리지)
            window.localStorage.removeItem("access");
            window.localStorage.removeItem("name");

            setIsLoggedIn(false);
            setLoginUser(null);

            // eslint-disable-next-line no-restricted-globals
            location.href = "/"
        } catch (error) {
            console.log("error: ", error);
        }
    }

    const loginHandler = async (e) => {
        e.preventDefault();
        const credentials = {username, password};
        await fetchLogin(credentials);
    }

    return (
        <div>
            <div>
                <h1>Home</h1>
                {isLoggedIn && <span>{loginUser}님 환영합니다.</span>}
                {isLoggedIn && <button onClick={fetchLogout}>Logout</button>}
            </div>
            {!isLoggedIn && <form id="loginForm" onSubmit={loginHandler}>
                <p><span className='label'>Username</span><input className='input-class' type="text" value={username}
                                                                 onChange={(e) => setUsername(e.target.value)}
                                                                 placeholder='username'/></p>
                <p><span className='label'>Password</span><input className='input-class' type="password"
                                                                 autoComplete='off' value={password}
                                                                 onChange={(e) => setPassword(e.target.value)}
                                                                 placeholder='password'/></p>
                <button type="submit">Login</button>
            </form>
            }
            {!isLoggedIn &&
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
            }
        </div>

    );
};

export default Home;
