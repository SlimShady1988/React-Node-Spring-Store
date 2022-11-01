import React, {useState} from 'react';
import {Button, Form, Modal} from "react-bootstrap";
import {createType} from "../../http/deviceApi";

const CreateTypeModal = ({show, onHide}) =>{
    const [value, setValue] = useState('')

    const addType = () => {
        createType({name: value}).then(data => {
            setValue('')
            onHide()
        });
    };


    return (
        <Modal show={show} onHide={onHide} size="lg" centered>
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Add New Type
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Control value={value}
                                  onChange={e=> setValue(e.target.value)}
                                  placeholder="Device name"/>

                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={onHide} variant={"outline-danger"}>CLOSE</Button>
                <Button onClick={addType} variant={"outline-success"}>ADD</Button>
            </Modal.Footer>
        </Modal>
    );

}

export default CreateTypeModal;