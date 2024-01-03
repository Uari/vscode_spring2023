import { Route, Routes } from 'react-router-dom';
import HomePage from './components/page/HomePage';
import BoardPage from './components/page/BoardPage';
import NoticePage from './components/page/NoticePage';
import NoticeWrite from './components/notice/NoticeWrite';
function App() {
  //Routes 처리 부분 UTL패턴 3번 부분
  //exact path가 완전히 일치해야한다
  return (
    <>
      <Routes>
        <Route path="/" exact={true} element={<HomePage />} />
        <Route path="/notice" element={<NoticePage />} />
        <Route path="/notice/write" element={<NoticeWrite />} />
        <Route path="/board" element={<BoardPage />} />
      </Routes>
    </>
  );
}

export default App;
