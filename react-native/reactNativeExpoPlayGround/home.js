import React, { Component } from "react";
import { View, Text, Button } from "react-native";
import { setName, deleteName } from './redux'
import {store} from './redux'
import { connect } from "react-redux";

export class Home extends Component {
  render() {
    return (
      <View style={{flex: 1, justifyContent: 'space-around', alignItems: 'center'}}>
        <Text style={{marginTop: 100}}>My name is {this.props.name}</Text>
        <View style={{flexDirection: 'row'}}>
          <Button 
            onPress={this.props.deleteName}
            title="deleteName"
          />
          <Button 
            onPress={() => this.props.setName('だい')}
            title="setName"
          />
          <Text style={{marginBottom: 100}}>Store: {JSON.stringify(store.getState())}</Text>
        </View>
      </View>
    )
  }
}

const mapStateToProps = state => ({
  name: state.user.name
})

const mapDispatchToProps = {
  setName,
  deleteName
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home)