import React, { useState } from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';

const AIModal = ({ onCancel, onConfirm }) => {
  const [projectDescription, setProjectDescription] = useState('');
  const [storyCount, setStoryCount] = useState('');
  const [sprintCount, setSprintCount] = useState('');

  return (
    <ModalOverlay>
      <ModalContainer>
        <ModalTitle>AI 추천</ModalTitle>

        <InputContainer>
          <Label>프로젝트 설명</Label>
          <StyledTextArea
            placeholder="프로젝트 설명을 적어주세요."
            value={projectDescription}
            onChange={(e) => setProjectDescription(e.target.value)}
          />
        </InputContainer>

        <InputContainer>
          <Description>스토리 및 스프린트 개수</Description>
          <Row>
            <Label>스토리</Label>
            <SmallInput
              type="number"
              value={storyCount}
              onChange={(e) => setStoryCount(e.target.value)}
            />
            <Label>개</Label>

            <Label>스프린트</Label>
            <SmallInput
              type="number"
              value={sprintCount}
              onChange={(e) => setSprintCount(e.target.value)}
            />
            <Label>개</Label>
          </Row>
        </InputContainer>

        <ButtonContainer>
          <CancelButton onClick={onCancel}>취소</CancelButton>
          <ConfirmButton onClick={onConfirm}>완료</ConfirmButton>
        </ButtonContainer>
      </ModalContainer>
    </ModalOverlay>
  );
};

AIModal.propTypes = {
  onCancel: PropTypes.func.isRequired,
  onConfirm: PropTypes.func.isRequired,
};

export default AIModal;

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
`;

const ModalContainer = styled.div`
  background-color: #fff;
  border-radius: 20px;
  padding: 25px;
  width: 450px;
  max-width: 90%;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
`;

const ModalTitle = styled.h2`
  font-size: 22px;
  font-weight: bold;
  color: #3c763d;
  margin-bottom: 15px;
`;

const InputContainer = styled.div`
  margin-bottom: 20px;
  text-align: left;
`;

const Description = styled.p`
  font-size: 14px;
  font-weight: bold;
  color: #333;
  margin-bottom: 10px;
`;

const Label = styled.label`
  display: inline-block;
  font-size: 14px;
  font-weight: bold;
  color: #333;
  margin-right: 5px;
`;

const StyledTextArea = styled.textarea`
  width: 100%;
  height: 80px; /* 높이를 조정하여 여러 줄이 보이도록 설정 */
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 14px;
  resize: none; /* 크기 조절 비활성화 */
  margin-top: 5px;
`;

const SmallInput = styled.input`
  width: 70px;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 14px;
  text-align: center;
`;

const Row = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
  gap: 10px;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  gap: 20px; /* 버튼 간격 */
`;

const CancelButton = styled.button`
  background-color: #dcdcdc;
  color: #333;
  border: none;
  border-radius: 20px;
  padding: 10px 20px;
  font-size: 14px;
  cursor: pointer;

  &:hover {
    background-color: #bfbfbf;
  }
`;

const ConfirmButton = styled.button`
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 20px;
  padding: 10px 20px;
  font-size: 14px;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;
