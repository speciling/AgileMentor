import React, { useState } from 'react';
import styled from 'styled-components';
import { FaTrash } from 'react-icons/fa';
import { useDrop } from 'react-dnd';
// eslint-disable-next-line import/no-unresolved
import SprintBar from '@components/common/SprintBar/index';

const Sprint = () => {
  const [sprintItems, setSprintItems] = useState([]);

  const [{ isOver }, drop] = useDrop(() => ({
    accept: 'BACKLOG_ITEM',
    drop: (item) => addBacklogToSprint(item),
    collect: (monitor) => ({
      isOver: monitor.isOver(),
    }),
  }));

  const addBacklogToSprint = (item) => {
    setSprintItems((prev) => [...prev, item]);
  };

  return (
    <SprintContainer ref={drop} isOver={isOver}>
      <Header>
        <HeaderLeft>
          <HeaderTitle>스프린트</HeaderTitle>
          <DeleteButton>
            <FaTrash style={{ marginRight: '4px' }} />
            삭제
          </DeleteButton>
        </HeaderLeft>
        <StartButton>스프린트 시작</StartButton>
      </Header>
      <SprintContent>
        {sprintItems.map((item, index) => (
          // eslint-disable-next-line react/no-array-index-key
          <SprintBar key={index} />
        ))}
        <AddTask>+ 작업 만들기</AddTask>
      </SprintContent>
    </SprintContainer>
  );
};

export default Sprint;

const SprintContainer = styled.div`
  background-color: ${(props) => (props.isOver ? '#e6f7ff' : '#ffffff')};
  border: ${(props) => (props.isOver ? '2px dashed #80a7f0' : 'none')};
  border-radius: 8px;
  padding: 1rem;
  box-shadow: 0 0.125rem 0.5rem rgba(0, 0, 0, 0.1);
  margin-bottom: 1.5rem;
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

const StartButton = styled.button`
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

const SprintContent = styled.div`
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
