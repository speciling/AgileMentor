import React from 'react';
import styled from 'styled-components';
import { FaFolderPlus } from 'react-icons/fa';

const CreateProjectButton = () => (
  <Button>
    <Icon />
    프로젝트 만들기
  </Button>
);

export default CreateProjectButton;

const Button = styled.button`
  display: flex;
  align-items: center;
  background-color: #b0c9f8;
  color: #fff;
  border: none;
  border-radius: 10px;
  padding: 12px 35px;
  font-size: 20px;
  font-family: 'PaperlogyBold';
  cursor: pointer;
`;

const Icon = styled(FaFolderPlus)`
  margin-right: 8px;
  font-size: 20px;
`;
