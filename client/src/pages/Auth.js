import React, {useContext, useState} from 'react';
import {Button, Card, Container, Form} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import {LOGIN_ROUTE, REGISTRATION_ROUTE, SHOP_ROUTE} from "../utils/consts";
import {login, registration} from "../http/userApi";
import {observer} from "mobx-react-lite";
import {Context} from "../index";
import {useNavigate} from 'react-router-dom'


const Auth = observer(() => {
    const {user} = useContext(Context);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const isLogin = window.location.pathname === LOGIN_ROUTE;
    const isRegistration = window.location.pathname === REGISTRATION_ROUTE;
    const navigate = useNavigate();


    const click = async () => {
        try {
            let data;
            if (isLogin) {
                data = await login(email, password)
            } else {
                data = await registration(email, password);
            }
            user.setUser(user);
            user.setIsAuth(true);
            navigate(SHOP_ROUTE)
        } catch (e) {
            alert(e.response.data.message)
        }
    }



    return (
        <div>
            <Container className="d-flex justify-content-center align-items-center"
                       style={{height: window.innerHeight - 54}}>
                <Card style={{width: 600}} className="p-5">
                    <h2 className="m-auto">{isLogin ? "Sign In" : "Sign Up"}</h2>
                    <Form className="d-flex flex-column">
                        <Form.Control value={email}
                                      onChange={(e) => setEmail(e.target.value)}
                                      className="mt-2" placeholder="email"/>
                        <Form.Control type="password" value={password}
                                      onChange={(e) => setPassword(e.target.value)}
                                      className="mt-2" placeholder="password"/>
                        <div className="d-flex justify-content-between mt-3">
                            {isLogin
                                ? <div>New here? <NavLink to={REGISTRATION_ROUTE}>Join Us</NavLink></div>
                                : <div>Has account? <NavLink to={LOGIN_ROUTE}>Login</NavLink></div>
                            }
                            <Button onClick={click}
                                    variant={"outline-primary"}>{isLogin ? "Sign In" : "Sign Up"}</Button>
                        </div>
                    </Form>
                </Card>
            </Container>
        </div>
    )
});

export default Auth;