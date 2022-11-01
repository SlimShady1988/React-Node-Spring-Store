import React, {useContext} from 'react';
import {Context} from "../index";
import {Button, Container, Nav, Navbar} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import {ADMIN_ROUTE, REGISTRATION_ROUTE, SHOP_ROUTE} from "../utils/consts";
import {observer} from "mobx-react-lite";
import {useNavigate} from 'react-router-dom'

const NavBar = observer(() => {
    const {user} = useContext(Context);
    const navigate = useNavigate();
    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <NavLink style={{color: "white"}} to={SHOP_ROUTE}>Economist</NavLink>
                {user.isAuth    ?
                    <Nav className="ml-auto" style={{color: "white"}}>
                        <Button onClick={()=> navigate(ADMIN_ROUTE)} variant={"outline-light"}>Dashboard</Button>
                        <Button onClick={()=>{
                            user.setIsAuth(false);
                            user.setUser({});
                            navigate(SHOP_ROUTE)
                        }} variant={"outline-light"} className="ms-3">Exit</Button>
                    </Nav>
                                :
                    <Nav className="ml-auto" style={{color: "white"}}>
                        <Button className={"ms-3"}
                                variant={"outline-light"}
                                onClick={() => user.setIsAuth(true)} >Sign In</Button>
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