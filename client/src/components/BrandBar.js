import React, {useContext} from 'react';
import {Context} from "../index";
import {Card} from "react-bootstrap";
import {observer} from "mobx-react-lite";

const BrandBar = observer(() => {
    const {device} = useContext(Context);
    return (
        <div className="d-flex">
            {device.brands.map(brand =>
                <Card key={brand.id}
                      style={{cursor: "pointer"}}
                      className="p-3"
                      onClick={() => device.setSelectedBrand(brand)}
                      border={brand.id === device.selectedBrand.id ? "danger" : "light"}>
                    {brand.name}
                </Card>

            )}
        </div>

    );
})

export default BrandBar;