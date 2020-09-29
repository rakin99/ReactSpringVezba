import React from "react"
import MojProfilPrikaz from "./MojProfilPrikaz";
import PrikazKarata from "./PrikazKarata";

import $ from "jquery"

class MojProfil extends React.Component{

    constructor(){
        super()
        this.state = {
            user:"",
            oldPassword:"",
            newPassword:"",
            karte:[]
        }
        this.handleClick=this.handleClick.bind(this)
        this.handleChange=this.handleChange.bind(this)
        this.cancel=this.cancel.bind(this)
    }

    cancel(){
        $('#prikazProfila').show()
        $('#tabelaKarata').show()
        $('#idKarta').hide()
    }

    componentDidMount(){
        var config={
            method: 'get',
            headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')}
          };

        fetch('http://localhost:8080/bioskop/users/'+this.props.state.logedUsername+'/null/null',config)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    user: res[0]
                })
            })
    }

    handleChange(event){
        console.log("Handle change")
        const {name,value} = event.target
        this.setState({
            [name]:value
        })
    }

    handleClick(){
        if(this.state.oldPassword === "" || this.state.newPassword === ""){
            alert("--Unesite novu lozinku!--")
        }else{

            const passwordChanger={
                oldPassword:this.state.oldPassword,
                newPassword:this.state.newPassword
            }
            var config={
                dataType: 'json',
                method: 'post',
                headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                body: JSON.stringify(passwordChanger)
            };


            // On submit of the form, send a POST request with the data to the server.
            fetch('http://localhost:8080/auth/change-password',config)
                .then(res => res.json())
                .then(res => {
                    if(res.result === "success"){
                        alert("--Uspesno ste izmenili lozinku!--")
                        window.location.reload()
                    }else{
                        alert("--Doslo je do greske prilikom izmene lozinke!--")
                    }
                })
        }
    }

    render(){
        const prikazKarata=this.state.user !== "" && <PrikazKarata users={this.props.users} cancel={this.cancel} state={this.state}/>
        return(
            <div id="mojProfil">
                <MojProfilPrikaz handleChange={this.handleChange} handleClick={this.handleClick} state={this.state} />
                {prikazKarata}
            </div>
        )
    }
}

export default MojProfil