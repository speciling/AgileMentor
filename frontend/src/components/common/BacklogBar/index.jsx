import React, { useState } from 'react';
import styled from 'styled-components';
import { FaUser, FaPlus, FaListUl } from 'react-icons/fa';
import { useDrag } from 'react-dnd';

// eslint-disable-next-line react/prop-types
const BacklogBar = ({ inSprint }) => {
  const [priority, setPriority] = useState('중'); // 우선순위 초기값: 중

  const [{ isDragging }, drag] = useDrag(() => ({
    type: 'BACKLOG_ITEM',
    item: { priority },
    collect: (monitor) => ({
      isDragging: monitor.isDragging(),
    }),
  }));

  const handlePriorityChange = () => {
    setPriority((prev) => {
      if (prev === '상') return '중';
      if (prev === '중') return '하';
      return '상';
    });
  };

  return (
    <BarContainer ref={!inSprint ? drag : null} isDragging={isDragging}>
      <LeftSection>
        {inSprint ? (
          <SprintIcon>
            <FaListUl />
          </SprintIcon>
        ) : (
          <Checkbox type="checkbox" />
        )}
        <Text>이름</Text>
      </LeftSection>
      <RightSection>
        <ActionButton color="#FFD771">
          <FaPlus style={{ marginRight: '4px' }} /> Story
        </ActionButton>
        <Dropdown>
          <DropdownText>To Do</DropdownText>
          <DropdownArrow>▼</DropdownArrow>
        </Dropdown>
        <PriorityBadge priority={priority} onClick={handlePriorityChange}>
          {priority}
        </PriorityBadge>
        <UserIcon>
          <FaUser />
        </UserIcon>
      </RightSection>
    </BarContainer>
  );
};

export default BacklogBar;

// Styled Components
const BarContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #eff5ff;
  padding: 0.8rem 1rem;
  border-radius: 8px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  opacity: ${(props) => (props.isDragging ? 0.5 : 1)};
  cursor: ${(props) => (props.isDragging ? 'grabbing' : 'grab')};
`;

const LeftSection = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
`;

const Checkbox = styled.input`
  width: 1rem;
  height: 1rem;
  cursor: pointer;
`;

const SprintIcon = styled.div`
  font-size: 1.2rem;
  color: #333;
`;

const Text = styled.span`
  font-size: 0.9rem;
  font-weight: bold;
  color: #333;
`;

const RightSection = styled.div`
  display: flex;
  align-items: center;
  gap: 12px;
`;

const ActionButton = styled.button`
  display: flex;
  align-items: center;
  background-color: ${(props) => props.color || '#ddd'};
  color: white;
  font-size: 0.9rem;
  font-weight: bold;
  border: none;
  border-radius: 4px;
  padding: 0.45rem 1.3rem;
  cursor: pointer;

  &:hover {
    opacity: 0.9;
  }
`;

const Dropdown = styled.div`
  display: flex;
  align-items: center;
  background-color: #bdc8ff;
  color: #ffffff;
  border-radius: 4px;
  padding: 0.25rem 1.3rem;
  cursor: pointer;
`;

const DropdownText = styled.span`
  font-size: 0.8rem;
  font-weight: bold;
`;

const DropdownArrow = styled.span`
  margin-left: 8px;
  font-size: 1.2rem;
`;

const PriorityBadge = styled.div`
  background-color: ${(props) =>
    // eslint-disable-next-line no-nested-ternary
    props.priority === '상'
      ? '#ff6b6b'
      : props.priority === '중'
      ? '#ffd700'
      : '#4caf50'};
  color: white;
  font-weight: bold;
  border-radius: 50%;
  width: 2rem;
  height: 2rem;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 0.9rem;
  cursor: pointer;

  &:hover {
    opacity: 0.9;
  }
`;

const UserIcon = styled.div`
  font-size: 1.2rem;
  color: #333;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #d8e2fc;
  border-radius: 50%;
  width: 2rem;
  height: 2rem;
  cursor: pointer;
`;
