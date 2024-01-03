import React from 'react'
import { Link } from 'react-router-dom'

//전체 조회결과 11건에서 한건씩 처리할 화면을 그려줄 함수를 따로 선언
// 함수를 태그이름으로 사용할 수 있다. - props 
const NoticeRow = ({notice}) => {
  return (
    <>
      <tr>
        <td>{notice.N_NO}</td>
        <td>
          <Link to={"/noticedetail/"+notice.N_NO} className='btn btn-primary'>{notice.N_TITLE}</Link>
        </td>
        <td>{notice.N_WRITER}</td>
      </tr>  
    </>
  )
}

export default NoticeRow