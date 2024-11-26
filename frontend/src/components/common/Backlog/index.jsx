import React from 'react';
import styled from 'styled-components';
import { FaTrash } from 'react-icons/fa';
// eslint-disable-next-line import/no-unresolved
import BacklogBar from '@components/common/BacklogBar/index';

const Backlog = () => (
  <BacklogContainer>
    <Header>
      <HeaderLeft>
        <HeaderTitle>백로그</HeaderTitle>
        <DeleteButton>
          <FaTrash style={{ marginRight: '4px' }} />
          삭제
        </DeleteButton>
      </HeaderLeft>
      <CreateButton>스프린트 만들기</CreateButton>
    </Header>
    <BacklogContent>
      {Array.from({ length: 10 }, (_, index) => (
        <BacklogBar key={index} />
      ))}
      <AddTask>+ 작업 만들기</AddTask>
    </BacklogContent>
  </BacklogContainer>
);

export default Backlog;

// Styled Components
const BacklogContainer = styled.div`
  background-color: #ffffff;
  border-radius: 8px;
  padding: 1rem;
  height: 30vh;
  overflow-y: auto;
`;

const Header = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
`;

const HeaderLeft = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
`;

const HeaderTitle = styled.h2`
  font-size: 1.2rem;
  font-weight: bold;
  color: #333;
`;

const DeleteButton = styled.button`
  display: flex;
  align-items: center;
  font-size: 0.9rem;
  color: #ff6b6b;
  background: none;
  border: none;
  cursor: pointer;

  &:hover {
    text-decoration: underline;
  }

  svg {
    font-size: 0.8rem;
  }
`;

const CreateButton = styled.button`
  background-color: #b0c9f8;
  color: #fff;
  font-size: 0.9rem;
  font-weight: bold;
  border: none;
  border-radius: 10px;
  padding: 0.6rem 1rem;
  cursor: pointer;

  &:hover {
    opacity: 0.9;
  }
`;

const BacklogContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

const AddTask = styled.div`
  font-size: 1rem;
  color: #666;
  cursor: pointer;

  &:hover {
    text-decoration: underline;
  }
`;
