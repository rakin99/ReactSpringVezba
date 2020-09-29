import React from "react"
import KupovinaKarteForm from "./KupovinaKarteForm"
import $ from "jquery"

class KupovinaKarte extends React.Component{
    constructor(){
        super()
        this.state = {
            slobodnaSedista:[],
            projekcija:"",
            izabranaSedista:[]
        }
        this.dodajSediste=this.dodajSediste.bind(this)
        this.ukloniSediste=this.ukloniSediste.bind(this)
        this.handleSubmit=this.handleSubmit.bind(this)
    }

    handleSubmit(e){
        e.preventDefault()
        var greska=false;
        console.log("Projekcija: "+JSON.stringify(this.state.projekcija))
        if(this.state.izabranaSedista.length === 0){
            alert("--Izaberite sediste!--")
        }else{
            console.log("Handle submit")
            this.state.izabranaSedista.map(s => {
                const karta = {
                    projekcija : this.state.projekcija.id,
                    sediste : s.redniBroj,
                    datumVremeProdaje : new Date(),
                    kupac : this.props.parentState.logedUsername
                }
                var config={
                    dataType: 'json',
                    contentType:"application/json; charset=utf-8",
                    method: 'post',
                    headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                    body: JSON.stringify(karta)
                  };
    
                  console.log("Saljem: "+JSON.stringify(karta))
        
                // On submit of the form, send a POST request with the data to the server.
                fetch('http://localhost:8080/bioskop/karte/add',config)
                    .then(res => res.json())
                    .then(res => {
                        if(res.result === "error"){
                            greska=true
                        }
                    })
            })
        }
        if(greska === true){
            alert("--Doslo je do greske prilikom kupovine karte!--")
        }else{
            alert("--Uspesno ste kupili kartu/e!--")
            this.props.cancel(e)
            this.props.setValue()
        }
    }

    componentDidMount(){
        const conf={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };

        this.setState({
            projekcija:this.props.state.projekcija,
            slobodnaSedista:this.props.state.brMesta
        })
    }

    dodajSediste(){
        const sediste=$('#slobodnaSedista').val();
        if(sediste === ""){
            alert("Izaberite sediste!")
        }else{
            const s = this.state.slobodnaSedista.filter(s => s.redniBroj==sediste)[0]
            this.state.izabranaSedista.push(s)
            this.state.izabranaSedista.sort(function(a, b){return a.redniBroj - b.redniBroj});
            this.setState(prevState =>{
                const update=prevState.slobodnaSedista.filter(s => s.redniBroj!=sediste)
                return{
                    slobodnaSedista:update
                }
            })
        }
    }

    ukloniSediste(){
        const sediste=$('#izabranaSedista').val();
        if(sediste === ""){
            alert("Izaberite sediste!")
        }else{
            const s = this.state.izabranaSedista.filter(s => s.redniBroj==sediste)[0]
            this.state.slobodnaSedista.push(s)
            this.state.slobodnaSedista.sort(function(a, b){return a.redniBroj - b.redniBroj});
            this.setState(prevState =>{
                const update=prevState.izabranaSedista.filter(s => s.redniBroj!=sediste)
                return{
                    izabranaSedista:update
                }
            })
        }
    }

    render(){
        return(
            <div id="kupovinaKarte">
                <KupovinaKarteForm handleSubmit={this.handleSubmit} dodajSediste={this.dodajSediste} ukloniSediste={this.ukloniSediste} state={this.state}/>
            </div>
        )
    }
}

export default KupovinaKarte