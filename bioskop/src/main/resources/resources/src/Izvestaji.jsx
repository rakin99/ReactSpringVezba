import React from "react"
import PrikazIzvestaja from "./PrikazIzvestaja"
import ZaglavljeIzvestaja from "./ZaglavljeIzvestaja"
import $ from "jquery"

class Izvestaji extends React.Component{
    constructor(){
        super()
        this.state = {
            izvestaji:[],
            pocetak:"",
            kraj:"",
            sort:"",
            ukupnoProjekcija:"",
            ukupnoKarata:"",
            ukupnoCena:""
        }
        this.handleChange=this.handleChange.bind(this)
        this.handleClick=this.handleClick.bind(this)
        this.handleChangeSort=this.handleChangeSort.bind(this)
    }

    handleClick(){
        console.log("Handle click")
        const config={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };
        
        const vremeOd=this.state.pocetak === "" ? "null":this.state.pocetak;
        const vremeDo=this.state.kraj === "" ? "null":this.state.kraj;
        const sort=this.state.sort === "" ? "null":this.state.sort;

        fetch('http://localhost:8080/bioskop/izvestaji/'+vremeOd+"/"+vremeDo+"/"+sort,config)
        .then(res => res.json())
        .then(res => {
            console.log(res)
            var ukupnoProjekcija=0;
            var ukupnoKarata=0;
            var ukupnoCena=0;
            res.map(i => {
                ukupnoProjekcija = i.brojProjekcija+ukupnoProjekcija
                ukupnoKarata = i.brojProdatihKarata+ukupnoKarata
                ukupnoCena = i.ukupnaCena+ukupnoCena
                
            })
            this.setState({
                izvestaji: res,
                ukupnoProjekcija:ukupnoProjekcija,
                ukupnoKarata:ukupnoKarata,
                ukupnoCena:ukupnoCena
            })
        })
    }

    handleChange(event){
        const {name,value} = event.target
        this.setState({
            [name]:value
        })
    }

    handleChangeSort(){
        const config={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };
        
        const vremeOd=this.state.pocetak === "" ? "null":this.state.pocetak;
        const vremeDo=this.state.kraj === "" ? "null":this.state.kraj;
        const sort=$('#idSortIzvestaji').val() === "" ? "null":$('#idSortIzvestaji').val();

        fetch('http://localhost:8080/bioskop/izvestaji/'+vremeOd+"/"+vremeDo+"/"+sort,config)
        .then(res => res.json())
        .then(res => {
            console.log(res)
            var ukupnoProjekcija=0;
            var ukupnoKarata=0;
            var ukupnoCena=0;
            res.map(i => {
                ukupnoProjekcija = i.brojProjekcija+ukupnoProjekcija
                ukupnoKarata = i.brojProdatihKarata+ukupnoKarata
                ukupnoCena = i.ukupnaCena+ukupnoCena
                
            })
            this.setState({
                izvestaji: res,
                ukupnoProjekcija:ukupnoProjekcija,
                ukupnoKarata:ukupnoKarata,
                ukupnoCena:ukupnoCena
            })
        })
    }

    render(){
        const prikazIzvestaja = this.state.izvestaji.length > 0 && <PrikazIzvestaja handleChangeSort={this.handleChangeSort} state={this.state} />
        return(
            <div id="izvestaji">
                <ZaglavljeIzvestaja handleChange={this.handleChange} handleClick={this.handleClick} />
                {prikazIzvestaja}
            </div>
        )
    }
}

export default Izvestaji