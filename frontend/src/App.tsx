import './App.css'
import {Navigate, Route, Routes} from "react-router-dom";
import MainPage from "./Pages/MainPage.tsx";
import CreateQuiz from "./Pages/CreateQuiz.tsx";
import TakeQuizList from "./Pages/TakeQuizList.tsx";
import UpdateQuizList from "./Pages/UpdateQuizList.tsx";
import UpdateQuiz from "./Pages/UpdateQuiz.tsx";
import TakeQuiz from "./Pages/TakeQuiz.tsx";

export default function App() {

  return (
    <Routes>
      <Route path="/" element={<MainPage/>}/>
      <Route path="/create" element={<CreateQuiz/>}/>
      <Route path="/take" element={<TakeQuizList/>}/>
      <Route path="/take/:id" element={<TakeQuiz/>}/>
      <Route path="/update" element={<UpdateQuizList/>}/>
      <Route path="/update/:id" element={<UpdateQuiz/>}/>
      <Route path="/*" element={<Navigate to={"/"}/>}/>
    </Routes>
  )
}
