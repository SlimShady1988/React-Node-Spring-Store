import React, {Component} from 'react';
import {Button, Container} from "react-bootstrap";
import CreateBrandModal from "../components/modals/CreateBrandModal";
import CreateDeviceModal from "../components/modals/CreateDeviceModal";
import CreateTypeModal from "../components/modals/CreateTypeModal";

class Admin extends Component {
    // [brandVisible, setBrandVisible] = useState(false);
    // [typeVisible, setTypeVisible] = useState(false);
    // [deviceVisible, setDeviceVisible] = useState(false);

    constructor(props) {
        super(props);
        this.state = {
            brandVisible: false,
            typeVisible: false,
            deviceVisible: false,
        }
    }



    render() {
        return(
            <Container className="d-flex flex-column">
                <Button onClick={()=>this.setState({typeVisible: true})} variant={"outline-dark"} className={"mt-4 p-2"}>Add Type</Button>
                <Button onClick={()=>this.setState({brandVisible: true})} variant={"outline-dark"} className={"mt-4 p-2"}>Add Brand</Button>
                <Button onClick={()=>this.setState({deviceVisible: true})} variant={"outline-dark"} className={"mt-4 p-2"}>Add Device</Button>
                <CreateTypeModal show={this.state.typeVisible} onHide={()=>this.setState({typeVisible: false})}/>
                <CreateBrandModal show={this.state.brandVisible} onHide={()=>this.setState({brandVisible: false})}/>
                <CreateDeviceModal show={this.state.deviceVisible} onHide={()=>this.setState({deviceVisible: false})}/>
            </Container>
        );
    }
}

export default Admin;