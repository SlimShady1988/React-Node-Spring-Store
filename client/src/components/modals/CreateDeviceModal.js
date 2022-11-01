import React, {useContext, useEffect, useState} from 'react';
import {Button, Col, Dropdown, Form, Modal, Row} from "react-bootstrap";
import {Context} from "../../index";
import {createDevice, fetchBrands, fetchTypes} from "../../http/deviceApi";
import {observer} from "mobx-react-lite";

const CreateDeviceModal  = observer(({show, onHide}) => {
    const {device} = useContext(Context)
    const [name, setName] = useState("");
    const [price, setPrice] = useState(0);
    const [file, setFile] = useState(null);
    const [info, setInfo] = useState([]);

    useEffect(() => {
        fetchTypes().then(data => device.setTypes(data));
        fetchBrands().then(data => device.setBrands(data));
    }, []);

    const addInfo = () => setInfo([...info, {title: "", description: "", number: Date.now()}])

    const removeInfo = (number) => setInfo(info.filter(i => i.number !== number))

    const changeInfo =  (key, value, number) => {
        setInfo(info.map(i => i.number === number ? {...i, [key]: value} : i));
    };

    const selectFile = e => setFile(e.target.files[0]);

    const addDevice = () => {
        const formData = new FormData();
        formData.append("name", name)
        formData.append("price", `${price}`)
        formData.append("img", file)
        formData.append("brandId", device.selectedBrand.id)
        formData.append("typeId", device.selectedType.id)
        formData.append("info", JSON.stringify(info))
        createDevice(formData).then(data => onHide());
    };

    return (
        <Modal show={show} onHide={onHide} size="lg" centered>
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Add New Device
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form><Row>
                    <Dropdown className={"mt-2 mb-2"}>
                        <Dropdown.Toggle> {device.selectedType.name || "Select Type"}</Dropdown.Toggle>
                        <Dropdown.Menu>
                            {device.types.map(type =>
                                <Dropdown.Item
                                    onClick={() => device.setSelectedType(type)}
                                    key={type.id}>
                                    {type.name}
                                </Dropdown.Item>
                            )}
                        </Dropdown.Menu>
                    </Dropdown>
                    <Dropdown className={"mt-2 mb-2"}>
                        <Dropdown.Toggle> {device.selectedBrand.name || "Select Brand"}</Dropdown.Toggle>
                        <Dropdown.Menu>
                            {device.brands.map(brand =>
                                <Dropdown.Item
                                    onClick={() => device.setSelectedBrand(brand)}
                                    key={brand.id}>
                                    {brand.name}
                                </Dropdown.Item>
                            )}
                        </Dropdown.Menu>
                    </Dropdown>
                </Row>
                    <Form.Control
                        value={name}
                        onChange={e=>setName(e.target.value)}
                        className={"mt-3"}
                        placeholder="Device name"/>
                    <Form.Control
                        value={price}
                        onChange={e=>setPrice(Number(e.target.value))}
                        className={"mt-3"}
                        placeholder="Device price"
                        type="number"/>
                    <Form.Control
                        onChange={selectFile}
                        className={"mt-3"}
                        placeholder="Device name"
                        type="file"/>
                    <hr/>
                    <Button onClick={addInfo} variant={"outline-success"}>Add Description</Button>
                    {info.map(i =>
                        <Row className={"mt-4"} key={i.number}>
                            <Col md={4}>
                                <Form.Control
                                    value={i.title}
                                    onChange={(e)=> changeInfo('title', e.target.value, i.number)}
                                    placeholder="name of characteristic"/>
                            </Col>
                            <Col md={4}>
                                <Form.Control
                                    value={i.description}
                                    onChange={(e)=> changeInfo('description', e.target.value, i.number)}
                                    placeholder="description of characteristic"/>
                            </Col>
                            <Col md={4}>
                                <Button onClick={()=> removeInfo(i.number)} variant={"outline-danger"}>DELETE</Button>
                            </Col>
                        </Row>
                    )}
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={onHide} variant={"outline-danger"}>CLOSE</Button>
                <Button onClick={addDevice} variant={"outline-success"}>ADD</Button>
            </Modal.Footer>
        </Modal>
    );
})

export default CreateDeviceModal;