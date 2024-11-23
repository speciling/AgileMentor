import React from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';

const MinModal = ({
  title,
  description,
  onCancel,
  onConfirm,
  value,
  onChange,
}) => (
  <ModalOverlay>
    <ModalContainer>
      <ModalTitle>{title}</ModalTitle>
      <ModalDescription>{description}</ModalDescription>
      <Input
        type="text"
        value={value}
        onChange={onChange}
        placeholder="입력해 주세요."
      />
      <ButtonContainer>
        <CancelButton onClick={onCancel}>취소</CancelButton>
        <ConfirmButton onClick={onConfirm}>완료</ConfirmButton>
      </ButtonContainer>
    </ModalContainer>
  </ModalOverlay>
);

MinModal.propTypes = {
  title: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
  onCancel: PropTypes.func.isRequired,
  onConfirm: PropTypes.func.isRequired,
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
};

export default MinModal;

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
  border-radius: 30px;
  padding: 20px;
  width: 400px;
  max-width: 90%;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
`;

const ModalTitle = styled.h2`
  font-size: 26px;
  font-weight: bold;
  margin-bottom: 25px;
`;

const ModalDescription = styled.p`
  font-size: 14px;
  color: #666;
  margin-bottom: 20px;
`;

const Input = styled.input`
  width: 100%;
  padding: 10px 0;
  background-color: #ededed;
  margin-bottom: 20px;
  border: none;
  font-size: 14px;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  gap: 10px;
`;

const CancelButton = styled.button`
  background-color: #dcdcdc;
  color: #333;
  border: none;
  border-radius: 30px;
  padding: 8px 20px;
  font-size: 14px;
  cursor: pointer;

  &:hover {
    background-color: #c0c0c0;
  }
`;

const ConfirmButton = styled.button`
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 30px;
  padding: 8px 20px;
  font-size: 14px;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;
