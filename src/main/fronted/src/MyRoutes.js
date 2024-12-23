import {Route, Routes} from 'react-router-dom';
import OAuth2Redirect from "./Oauth2Redirect";
import Home from "./Home";
import Admin from "./Admin";

const MyRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/admin" element={<Admin />} />
            <Route path="/oauth2-jwt-header" element={<OAuth2Redirect/>}/>
        </Routes>
    );
}

export default MyRoutes;
