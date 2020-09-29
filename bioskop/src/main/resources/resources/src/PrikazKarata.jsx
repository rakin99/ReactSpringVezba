import React from "react"
import PrikazKarte from "./PrikazKarte";
import TabelaKarte from "./TabelaKarte";
import $ from "jquery"

class PrikazKarata extends React.Component{
    constructor(){
        super()
        this.state={
            karte: [],
            karta: "",
            projekcija:"",
            k:false
        }
        this.handleClick=this.handleClick.bind(this)
    }

    componentDidMount(){
        console.log("User: "+JSON.stringify(this.props.state))
        const config={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };

        fetch('http://localhost:8080/bioskop/karte/'+this.props.state.user.korisnickoIme,config)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    karte: res
                })
            })
    }

    handleClick(event){
        const id=event.target.name
        const karta=this.state.karte.filter(k => k.id == id)[0]
        
        const config={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };

        fetch('http://localhost:8080/bioskop/projekcije/'+karta.projekcija,config)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    projekcija: res
                })
            })
        
        this.setState({karta:karta})
        
        $('#idKorisnik').hide();
        $('#prikazProfila').hide();
        $('#tabelaKarata').hide();
        $('#idKarta').show()

    }
    
    render(){
        const prikazKarte = this.state.karta !== "" && <PrikazKarte users={this.props.users} cancel={this.props.cancel} state={this.state} />
        const prikazKarata = this.state.karte.length !== 0 && <TabelaKarte handleClick={this.handleClick} state={this.state}/>
        return(
            <div style={{display: "inline-block", marginLeft: "150px"}} id="prikazKarata">
                {prikazKarata}
                {prikazKarte}
            </div>
        )
    }
}

export default PrikazKarata