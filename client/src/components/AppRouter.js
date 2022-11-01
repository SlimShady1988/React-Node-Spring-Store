import React, {useContext} from 'react';
import {Route, Routes} from "react-router-dom";
import {authRoutes, publicRoutes} from "../routes";
import Shop from "../pages/Shop";
import {Context} from "../index";

function AppRouter() {
        const {user} = useContext(Context)
        return (
            <Routes>
                {user.isAuth && authRoutes.map(({path, Component}) => {
                      return  <Route exact key={path} path={path} element={<Component/>} />
                    }
                )}
                {publicRoutes.map(({path, Component}) => {
                        return  <Route exact key={path} path={path} element={<Component/>} />
                    }
                )}
                <Route path="*" element={<Shop/>}/>
            </Routes>
        );
}

export default AppRouter;