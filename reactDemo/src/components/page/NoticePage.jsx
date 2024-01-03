import React, { useEffect, useState } from 'react';
import Header from '../include/Header';
import { Button, Table } from 'react-bootstrap';
import Bottom from '../include/Bottom';
import { useNavigate } from 'react-router';
import NoticeRow from '../notice/NoticeRow';
import { noticeListDB } from '../../service/dbLogic';

const NoticePage = () => {
  const navigate = useNavigate();
  const [gubun, setGubun] = useState('');
  const [keyword, setKeyword] = useState('');
  const [notices, setNotices] = useState([{}]);

  useEffect(() => { //effect 훅 이다 - 이름앞에 useXXX형태이면 Hook이다 - 16.8버전 
    noticeList();
  }, [setGubun, setKeyword, setNotices]);

  const noticeList = async () => {
    console.log('noticeList호출');
    const gubun = document.querySelector('#gubun').value; //n_title, n_writer, n_content
    const keyword = document.querySelector('#keyword').value;
    console.log(`${gubun}, ${keyword}`);
    const notice = {
      gubun: gubun,
      keyword: keyword,
    };
    const res = await noticeListDB(notice); //스프링 프로젝트에서 요청하기 - 비동기 상황 연출 - 지연 - 8000번 서버 - 1521번 경유
    document.querySelector('#gubun').value = '분류선택';
    document.querySelector('#keyword').value = '';
    console.log(res);
    setNotices(res.data);
  };

  const noticeSearch = (event) => {
    console.log(`noticeSearch ==> ${event.key}`);
    if (event.key === 'Enter') {
      noticeList();
    }
  };

  return (
    <>
      <Header />
      <div className="container">
        <div className="page-header">
          <h2>
            공지관리 <small>글목록</small>
          </h2>
          <hr />
        </div>

        <div className="row">
          <div className="col-3">
            <select id="gubun" className="form-select" aria-label="분류선택">
              <option defaultValue>분류선택</option>
              <option value="n_title">제목</option>
              <option value="n_writer">작성자</option>
              <option value="n_content">내용</option>
            </select>
          </div>
          <div className="col-6">
            <input
              type="text"
              id="keyword"
              className="form-control"
              placeholder="검색어를 입력하세요"
              aria-label="검색어를 입력하세요"
              aria-describedby="btn_search"
              onKeyUp={noticeSearch}
            />
          </div>
          <div className="col-3">
            <Button variant="danger" id="btn_search" onClick={noticeList}>
              검색
            </Button>
          </div>
        </div>

        <div className="board-list">
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>#</th>
                <th>제목</th>
                <th>작성자</th>
              </tr>
            </thead>
            <tbody>
              {notices && notices.map((notice, key) => 
                <NoticeRow key={key} notice={notice} />
              )}
            </tbody>
          </Table>

          <div
            style={{
              margin: '10px',
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              justifyContent: 'center',
              width: '100%',
            }}
          ></div>

          <hr />
          <div className="boardlist-footer">
            <Button variant="warning" onClick={noticeList}>
              전체조회
            </Button>
            &nbsp;
            <Button
              variant="success"
              onClick={() => {
                navigate(`/notice/write`);
              }}
            >
              글쓰기
            </Button>
          </div>
        </div>
      </div>
      <Bottom />
    </>
  );
};

export default NoticePage;
