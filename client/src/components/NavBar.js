import React, {useContext} from 'react';
import {Context} from "../index";
import {Button, Container, Nav, Navbar} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import {ADMIN_ROUTE, LOGOUT_ROUTE, REGISTRATION_ROUTE, LOGIN_ROUTE,SHOP_ROUTE} from "../utils/consts";
import {observer} from "mobx-react-lite";
import {useNavigate} from 'react-router-dom'

const NavBar = observer(() => {
    const {user} = useContext(Context);
    const navigate = useNavigate();

    const logout = () => {
        user.setUser({})
        user.setIsAuth(false)
        if (process.env.REACT_APP_API_URL.endsWith("8080/")){
            navigate(LOGOUT_ROUTE)
        } else {
            navigate(SHOP_ROUTE)
        }
    }

    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <NavLink style={{color: "white"}} to={SHOP_ROUTE}>Economist</NavLink>
                {user.isAuth    ?
                    <Nav className="ml-auto" style={{color: "white"}}>
                        <Button onClick={()=> navigate(ADMIN_ROUTE)} variant={"outline-light"}>Dashboard</Button>
                        <Button onClick={()=>logout()} variant={"outline-light"} className="ms-3">Exit</Button>
                    </Nav>
                                :
                    <Nav className="ml-auto" style={{color: "white"}}>
                        <Button className={"ms-3"}
                                variant={"outline-light"}
                                onClick={() => navigate(LOGIN_ROUTE)} >Sign In</Button>
                        <Button className={"ms-3"}
                                variant={"outline-light"}
                                onClick={()=> navigate(REGISTRATION_ROUTE)} >Sign Up</Button>
                    </Nav>
                }
            </Container>
        </Navbar>
    );
})

export default NavBar;