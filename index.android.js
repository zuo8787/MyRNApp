import React, { Component } from 'react';
import {
  AppRegistry,
  Text,
    View
} from 'react-native';

import MyFirstRN from './myfirstrn'
import MySecondRN from './mysecondrn'

/*class Root extends Component {
    render() {
        switch (this.props.entrance) {
            case 1:
                return <MyFirstRN/>
            case 2:
                return <MySecondRN/>

        }

    }
}

AppRegistry.registerComponent('MyRN', () => Root);
*/
AppRegistry.registerComponent('MyRNActivity', () => MyFirstRN);
AppRegistry.registerComponent('MyRNActivity2', () => MySecondRN);
