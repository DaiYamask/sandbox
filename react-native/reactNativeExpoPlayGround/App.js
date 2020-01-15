import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Home } from './home';
import { store } from './redux';
import { Provider } from 'react-redux';

export default function App() {
  return (
      <Provider store={store} >
        <Home />
      </Provider>
  );
}