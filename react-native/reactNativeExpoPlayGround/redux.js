import {combineReducers, createStore} from 'redux'

export const deleteName = () => ({
  type: 'DELETE_NAME',
  name: ''
})

export const setName = name => ({
  type: 'ADD_NAME', 
  name: name
})

const INITIAL_STATE = {
  name: 'NoName'
}

const reducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case 'ADD_NAME':
      return {...state, name: action.name}
    case 'DELETE_NAME':
      return {...state, name: ''}
    default:
      return state
  }
}

export const reducers = combineReducers({
  user: reducer
})

export const store = createStore(reducers)

console.log(store.getState)
console.table(store.getState)
console.log(typeof store.getState)
console.log(store)