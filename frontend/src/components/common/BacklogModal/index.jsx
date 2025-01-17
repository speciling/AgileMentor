import React, { useState } from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';

const BacklogModal = ({
  onCancel,
  onConfirm,
  initialPriority,
  initialStatus,
  initialAssignee,
  assignees,
  initialStory,
  stories,
  initialDescription,
  initialModalTitle,
}) => {
  const [modalTitle, setModalTitle] = useState(initialModalTitle || '백로그 이름');
  const [story, setStory] = useState(initialStory || '');
  const [description, setDescription] = useState(initialDescription || '');
  const [assignee, setAssignee] = useState(initialAssignee || '');
  const [priority, setPriority] = useState(initialPriority || '');
  const [status, setStatus] = useState(initialStatus || '');

  return (
    <ModalOverlay>
      <ModalContainer>
        <TitleContainer>
          <EditableTitle
            type="text"
            value={modalTitle}
            onChange={(e) => setModalTitle(e.target.value)}
          />
          <TitleHint>제목을 클릭하여 수정하세요.</TitleHint>
        </TitleContainer>

        <InputContainer>
          <Label>상위 스토리</Label>
          <Select value={story} onChange={(e) => setStory(e.target.value)}>
            <option value="">스토리 선택하기</option>
            {stories.map((s) => (
              <option key={s} value={s}>
                {s}
              </option>
            ))}
          </Select>
        </InputContainer>

        <InputContainer>
          <Label>백로그 설명</Label>
          <StyledTextArea
            placeholder="백로그 설명을 입력하세요."
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </InputContainer>

        <InputContainer>
          <Row>
            <Column>
              <Label>담당자 선택</Label>
              <Select value={assignee} onChange={(e) => setAssignee(e.target.value)}>
                <option value="">선택하기</option>
                {assignees.map((user) => (
                  <option key={user} value={user}>
                    {user}
                  </option>
                ))}
              </Select>
            </Column>

            <Column>
              <Label>우선순위 선택</Label>
              <Select value={priority} onChange={(e) => setPriority(e.target.value)}>
                <option value="">선택하기</option>
                <option value="high">높음</option>
                <option value="medium">중간</option>
                <option value="low">낮음</option>
              </Select>
            </Column>

            <Column>
              <Label>진행 상태 설정</Label>
              <Select value={status} onChange={(e) => setStatus(e.target.value)}>
                <option value="">선택하기</option>
                <option value="todo">To Do</option>
                <option value="inprogress">In Progress</option>
                <option value="done">Done</option>
              </Select>
            </Column>
          </Row>
        </InputContainer>

        <ButtonContainer>
          <CancelButton onClick={onCancel}>취소</CancelButton>
          <ConfirmButton onClick={onConfirm}>확인</ConfirmButton>
        </ButtonContainer>
      </ModalContainer>
    </ModalOverlay>
  );
};

BacklogModal.propTypes = {
  onCancel: PropTypes.func.isRequired,
  onConfirm: PropTypes.func.isRequired,
  initialPriority: PropTypes.oneOf(['', 'high', 'medium', 'low']),
  initialStatus: PropTypes.oneOf(['', 'todo', 'inprogress', 'done']),
  assignees: PropTypes.arrayOf(PropTypes.string),
  initialAssignee: PropTypes.string,
  initialStory: PropTypes.string,
  stories: PropTypes.arrayOf(PropTypes.string),
  initialDescription: PropTypes.string,
  initialModalTitle: PropTypes.string,
};

BacklogModal.defaultProps = {
  initialPriority: '',
  initialStatus: '',
  assignees: [],
  initialAssignee: '',
  initialStory: '',
  stories: [],
  initialDescription: '',
  initialModalTitle: '',
};

export default BacklogModal;

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
  width: 500px;
  max-width: 90%;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
`;

const TitleContainer = styled.div`
  margin-bottom: 20px;
  text-align: center;
`;

const EditableTitle = styled.input`
  font-size: 22px;
  font-weight: bold;
  color: #3c763d;
  border: none;
  text-align: center;
  background: #f9f9f9;
  width: 100%;
  outline: none;
  border-bottom: 2px dashed #ccc;
  padding: 5px;

  &:hover {
    background: #eef;
  }

  &:focus {
    border-bottom: 2px solid #007bff;
    background: #fff;
  }
`;

const TitleHint = styled.p`
  font-size: 12px;
  color: #666;
  text-align: center;
  margin-top: 5px;
  margin-bottom: 20px;
  font-style: italic;
`;

const InputContainer = styled.div`
  margin-bottom: 20px;
  text-align: left;
`;

const Label = styled.label`
  display: inline-block;
  font-size: 14px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
`;

const StyledTextArea = styled.textarea`
  width: 100%;
  height: 80px;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 14px;
  resize: none;
`;

const Select = styled.select`
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 14px;
  color: #333;

  &:focus {
    border-color: #007bff;
    outline: none;
  }
`;

const Row = styled.div`
  display: flex;
  justify-content: space-between;
  gap: 20px;
`;

const Column = styled.div`
  flex: 1;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  gap: 20px;
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
