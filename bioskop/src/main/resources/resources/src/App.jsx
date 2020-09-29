import React, {Component} from "react"
import TodoItem from "./TodoItem"
import todosData from "./todosData"

class App extends Component{

  constructor(){
    super()
    this.state = {
      todos: todosData
    }
    this.handleChange=this.handleChange.bind(this)
  }

  handleChange(id){
    this.setState(prevState => {
      const updateTodos=prevState.todos.map(todo =>{
        if(todo.id === id){
          todo.completed= !todo.completed
        }
        return todo
      })
      return{
        todos: updateTodos
      }
    })
  }

  render(){
    const item=this.state.todos.map(i => 
    <TodoItem key={i.id} it={i} handleChange={this.handleChange}/>)

    return(
      <div className="todo-list">
        {item}
      </div>
    )
  }
}

export default App
