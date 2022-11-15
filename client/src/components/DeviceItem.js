import {Card, Col} from "react-bootstrap";
import Image from "react-bootstrap/Image";
import star from "../assets/Star 1.png";
import {useNavigate} from 'react-router-dom'
import {observer} from "mobx-react-lite";
import {DEVICE_ROUTE} from "../utils/consts";

const DeviceItem = observer(({device}) => {
    const navigate = useNavigate();
    const path = process.env.REACT_APP_API_URL.endsWith("8080/")
        ? process.env.REACT_APP_API_URL + "api/"
        : process.env.REACT_APP_API_URL;
    return (

            <Col md={3} className={"mt-3"} onClick={() => navigate(DEVICE_ROUTE + "/" + device.id)}>
                <Card style={{width: 150, cursor:"pointer" }} border={"light"}>
                    <Image width={150} height={150} src={path + device.img}/>
                    <div className="text-black-50 d-flex justify-content-between align-items-center">
                        <div>
                            {device.brandId}
                        </div>
                        <div className="d-flex align-items-center">
                            <div>{device.rating}</div>
                            <Image  width={15} height={15} src={star}/>

                        </div>
                    </div>
                    <div>{device.name}</div>

                </Card>
            </Col>
    );
}
)

export default DeviceItem;