import React from "react"

function PrikazIzvestaja(props){
    const izvestaj=props.state.izvestaji.map(i =>   <tr key={i.id}>
                                                        <td>{i.film}</td>
                                                        <td>{i.brojProjekcija}</td>
                                                        <td>{i.brojProdatihKarata}</td>
                                                        <td>{i.ukupnaCena}</td>
                                                    </tr>
                                            )

    return(
        <div>
            <label>
                        Sortiraj po:
                            <select id="idSortIzvestaji" onChange={props.handleChangeSort}>
                                <option value=""></option>
                                <option value="brProjekcija-asc">Broju projekcija, rastuce</option>
                                <option value="brProjekcija-desc">Broju projekcija, opadajuce</option>
                                <option value="brProdatihKarata-asc">Broju prodatih karata, rastuce</option>
                                <option value="brProdatihKarata-desc">Broju prodatih karata, opadajuce</option>
                                <option value="ukupnaCenaProdatihKarata-asc">Ukupnoj ceni, rastuce</option>
                                <option value="ukupnaCenaProdatihKarata-desc">Ukupnoj ceni, opadajuce</option>
                            </select>
                    </label>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Film</th>
                                <th>Broj projekcija</th>
                                <th>Broj prodatih karata</th>
                                <th>Ukupna cena prodatih karata</th>
                            </tr>
                        </thead>
                        <tbody>
                            {izvestaj}
                            <tr>
                                <td>Ukupno:</td>
                                <td>{props.state.ukupnoProjekcija}</td>
                                <td>{props.state.ukupnoKarata}</td>
                                <td>{props.state.ukupnoCena}</td>
                            </tr>
                        </tbody>
                    </table>
        </div>
    )
}

export default PrikazIzvestaja