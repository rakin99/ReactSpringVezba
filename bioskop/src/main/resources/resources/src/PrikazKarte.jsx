import React from "react"
import Karta from "./Karta"

class PrikazKarte extends React.Component{

    constructor(){
        super()
        this.state = {
            projekcija:""
        }
    }

    render(){
        const karta=this.state.karta !== "" && <Karta users={this.props.users} cancel={this.props.cancel} projekcija={this.props.state.projekcija} karta={this.props.state.karta}/>
        return(
            <div>
                {karta}
            </div>
        )
    }
}

export default PrikazKarte