import React from "react"

function FilterProjekcija(props){
    const optTip=props.state.tipoviZaIzbor.map(p => <option key={p} value={p}>{p}</option>)
    const optSale=props.state.saleZaIzbor.map(s => <option key={s.id} value={s.naziv}>{s.naziv}</option>)
    
    return(
        <thead>
            <tr>
                <th>
                    Film
                </th>
                <th>
                    Vreme prikazivanja
                </th>
                <th>
                    Tip projekcije
                </th>
                <th>
                    Sala
                </th>
                <th>
                    Cena karte
                </th>
            </tr>
            <tr>
                <th>
                    <input 
                        id="idFilm"
                        type="text" 
                        placeholder="Film..." 
                        onChange={props.filter}
                    />
                </th>
                <th>
                    Od:
                    <input 
                        onChange={props.filter} 
                        type="datetime-local" 
                        id="idDatumOd"
                    />
                    <br/>
                    Do:
                    <input 
                        onChange={props.filter} 
                        type="datetime-local" 
                        id="idDatumDo"
                    />
                </th>
                <th>
                    <label>
                        <select id="idTip" onChange={props.filter} name="tipProjekcije">
                            <option value="">--Izaberi tip--</option>
                            {optTip}
                        </select>
                    </label>
                </th>
                <th>
                    <label>
                        <select id="idSala" onChange={props.filter} name="sala">
                            <option value="">--Izaberi salu--</option>
                            {optSale}
                        </select>
                    </label>
                </th>
                <th>
                    <input 
                        id="idCenaOd"
                        type="text" 
                        placeholder="Od..." 
                        onChange={props.filter}
                    />
                    <br/>
                    <input 
                        id="idCenaDo"
                        type="text" 
                        placeholder="Do..." 
                        onChange={props.filter}
                    />
                </th>
            </tr>
        </thead>
    )
}

export default FilterProjekcija