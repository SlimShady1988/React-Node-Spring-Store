import React, {useEffect, useState} from 'react';
import {Button, Card, Col, Container, Image, Row} from "react-bootstrap";
import star from "../assets/Star 1.png";
import {useParams} from "react-router-dom";
import {fetchOneDevice} from "../http/deviceApi";

const DevicePage = () => {
    const [device, setDevice] = useState({info: []});
    const {id} = useParams();
    useEffect(() => {
        fetchOneDevice(id).then(data => setDevice(data))
    }, [])
    const path = process.env.REACT_APP_API_URL.endsWith("8080/")
        ? process.env.REACT_APP_API_URL + "api/"
        : process.env.REACT_APP_API_URL;
    return(
        <Container className="mt3">
            <Row>
                <Col md={4}><Image width={300} height={300} src={path + device.img}/></Col>
                <Col md={4}>
                    <div className="d-flex flex-column align-items-center">
                        <h2>{device.name}</h2>
                        <div
                            className="d-flex justify-content-center align-items-center"
                            style={{
                                background: `url(${star}) no-repeat center center`, width: 240, height: 240,
                                backgroundSize: "cover", fontSize: 64
                            }}
                        >{device.rating}</div>

                    </div>
                </Col>
                <Col md={4}>
                    <Card
                        className="d-flex justify-content-around align-items-center"
                        style={{width: 300, height: 300, fontSize: 32, border: '5px solid lightgray'}}
                    >
                        <h3> From {device.price} $</h3>
                        <Button variant={"outline-dark"}>Add</Button>
                    </Card>
                </Col>
            </Row>
            <Row className="d-flex flex-column m-3">
                <h1>Characteristics</h1>
                {device.info.map((info, index) =>
                    <Row key={info.id} style={{background: index % 2 === 0 ? "lightgray" : "transparent", padding: 10}}>
                        {info.title} : {info.description}
                    </Row>
                )}
            </Row>

            </Container>
        );
}

export default DevicePage;