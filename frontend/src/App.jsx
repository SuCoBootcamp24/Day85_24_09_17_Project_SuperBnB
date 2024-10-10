// Globales Styleguide z.b. mixin und variablen

import { useState } from 'react';
import './App.scss';
import PopDestinations from './Components/landingPage/pop-destinations/PopDestinations';

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <PopDestinations />
    </>
  )
}

export default App
