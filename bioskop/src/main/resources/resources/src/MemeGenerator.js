import React from "react"

class MemeGenerator extends React.Component{
    
    constructor(){
        super()
        this.state = {
            topText: "",
            bottomText: "",
            randomImg: "http://i.imgflip.com/lbij.jpg",
            allMemeImgs: []
        }
        this.handleChange=this.handleChange.bind(this)
    }

    componentDidMount(){
        fetch("https://api.imgflip.com/get_memes")
        .then(response => response.json())
        .then(response => {
            const {memes} =response.data
            this.setState({allMemeImgs: memes})
        })
    }

    handleChange(event){
        const {name, value} = event.target
        this.setState({[name]:value})
    }

    render(){
        return(
            <div>
                <form>
                    <input 
                        placeholder="Top text" 
                        value={this.state.topText} 
                        type="text" 
                        onChange={this.handleChange}
                        name="topText" 
                    />
                    <br/>
                    <input 
                        placeholder="Bottom text" 
                        value={this.state.bottomText} 
                        type="text" 
                        onChange={this.handleChange}
                        name="bottomText" 
                    />
                    <br/>
                    <button>Gen</button>
                </form>
                <div    className="meme">
                    <img src={this.state.randomImg} ald=""/>
                    <h2>{this.state.topText}</h2>
                    <h2>{this.state.bottomText}</h2>
                </div>
            </div>
        )
    }
}

export default MemeGenerator